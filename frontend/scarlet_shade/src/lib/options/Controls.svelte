<script>
    import { onMount, onDestroy } from "svelte";
    import "$style/components/controlStyle.css";
    import Component from "../Component.svelte";

    export let close;
    export let keyboardControl;
    export let gamepadControl;

    let waiting = null;
    let activeControl = "keyboard";
    
    let gamepadLoop = null;
    let previousButtons = [];
    let previousAxes = [];

    // Mapeamento original para manter compatibilidade com o banco
    const GAMEPAD_BUTTON_MAP = {
        0: "A", 1: "B", 2: "X", 3: "Y", 4: "LEFT_BUMPER",
        5: "RIGHT_BUMPER", 6: "LEFT_TRIGGER", 7: "RIGHT_TRIGGER",
        8: "BACK", 9: "START", 10: "ANALOG_CLICK", 11: "ANALOG_CLICK_RIGHT",
        12: "ANALOG_UP", 13: "ANALOG_DOWN", 14: "ANALOG_LEFT", 15: "ANALOG_RIGHT"
    };

    // --- TECLADO ---
    function onKeyPress(event) {
        if (!waiting || activeControl !== "keyboard") return;
        
        event.preventDefault();
        const newKey = event.code === "Space" ? "SPACE" : event.key.toUpperCase();
        
        keyboardControl[waiting] = newKey;
        keyboardControl = { ...keyboardControl }; // Atualiza interface
        
        stopWaiting();
    }

    // --- GAMEPAD ---
    function checkGamepadInput() {
        // Se não estivermos esperando um input, apenas continua o loop sem processar lógica pesada
        if (!waiting || activeControl !== "gamepad") {
            gamepadLoop = requestAnimationFrame(checkGamepadInput);
            return;
        }

        const pads = navigator.getGamepads();
        const gp = pads[0];

        if (!gp) {
            gamepadLoop = requestAnimationFrame(checkGamepadInput);
            return;
        }

        // Botões
        gp.buttons.forEach((btn, i) => {
            const wasPressed = previousButtons[i] || false;
            if (btn.pressed && !wasPressed) {
                bindGamepad(GAMEPAD_BUTTON_MAP[i] || `BUTTON_${i}`);
            }
            previousButtons[i] = btn.pressed;
        });

        // Eixos (Analógicos/D-Pad)
        gp.axes.forEach((value, index) => {
            const prev = previousAxes[index] || 0;
            if (Math.abs(value) > 0.7 && Math.abs(prev) <= 0.7) {
                if (index === 0) bindGamepad(value > 0 ? "ANALOG_RIGHT" : "ANALOG_LEFT");
                if (index === 1) bindGamepad(value > 0 ? "ANALOG_DOWN" : "ANALOG_UP");
            }
            previousAxes[index] = value;
        });

        gamepadLoop = requestAnimationFrame(checkGamepadInput);
    }

    function bindGamepad(buttonName) {
        gamepadControl[waiting] = buttonName;
        gamepadControl = { ...gamepadControl }; // Atualiza interface
        stopWaiting();
    }

    // --- CONTROLE DE ESTADO ---
    function startRebind(action, type) {
        waiting = action;
        activeControl = type;

        if (type === "keyboard") {
            window.addEventListener("keydown", onKeyPress);
        }
    }

    function stopWaiting() {
        waiting = null;
        window.removeEventListener("keydown", onKeyPress);
    }

    // Função crucial para fechar sem bugar
    function handleClose() {
        stopWaiting();
        if (gamepadLoop) cancelAnimationFrame(gamepadLoop);
        close();
    }

    onMount(() => {
        gamepadLoop = requestAnimationFrame(checkGamepadInput);
    });

    onDestroy(() => {
        stopWaiting();
        if (gamepadLoop) cancelAnimationFrame(gamepadLoop);
    });
</script>

<Component close={handleClose} action="control">
    <h2>Controls</h2>

    <div class="controls_box">
        <h5>Keyboard</h5>
        <div class="controls_container">
            {#each Object.keys(keyboardControl) as action}
                <!-- svelte-ignore a11y_click_events_have_key_events -->
                <!-- svelte-ignore a11y_no_static_element_interactions -->
                <div
                    on:click={() => startRebind(action, "keyboard")}
                    class="control_item">
                        
                    {#if waiting === action && activeControl === "keyboard"}
                        Press...
                    {:else}
                        {@html `${action}: <br><span class="control_value">${keyboardControl[action]}</span>`}
                    {/if}
                </div>
            {/each}
        </div>

        <input type="hidden" name="keyboard" value={JSON.stringify(keyboardControl)} />

        <h5>Gamepad</h5>
        <div class="controls_container">
            {#each Object.keys(gamepadControl) as action}
                <!-- svelte-ignore a11y_click_events_have_key_events -->
                <!-- svelte-ignore a11y_no_static_element_interactions -->
                <div
                    on:click={() => startRebind(action, "gamepad")}
                    class="control_item">
                        
                    {#if waiting === action && activeControl === "gamepad"}
                        Press...
                    {:else}
                        {@html `${action}: <br><span class="control_value">${gamepadControl[action]}</span>`}
                    {/if}
                </div>
            {/each}
        </div>

        <input type="hidden" name="gamepad" value={JSON.stringify(gamepadControl)} />
    </div>
</Component>