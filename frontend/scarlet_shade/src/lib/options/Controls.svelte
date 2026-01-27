<script>

    import "$style/components/controlStyle.css";

    import Component from "../Component.svelte";

    export let close;
    export let keyboardControl;
    export let gamepadControl;

    let waiting = null;
    let activeControl = "keyboard";
    
    function startRebind(action) {
        waiting = action;
        window.addEventListener("keydown", onKeyPress);
    }

    function getActiveControls() {
        return activeControl === "keyboard" ? keyboardControl : gamepadControl;
    }

    function onKeyPress(event) {
        event.preventDefault();

        if (!waiting) return;

        const controls = getActiveControls();
        controls[waiting] =
            event.code === "Space" ? "SPACE" : event.key.toUpperCase();

        if (activeControl === "keyboard") {
            keyboardControl = keyboardControl;
        } else {
            gamepadControl = gamepadControl;
        }

        waiting = null;
        window.removeEventListener("keydown", onKeyPress);
    }
</script>

<Component close={close} action="control">
    
    <h2>Controls</h2>

    <div class="controls_box">
            
        <h5>Keyboard</h5>
            
        <div class="controls_container">
                    
            {#each Object.keys(keyboardControl) as action, i}
                        
                <!-- svelte-ignore a11y_click_events_have_key_events -->
                <!-- svelte-ignore a11y_no_static_element_interactions -->
                <div
                    on:click={() => {activeControl = "keyboard"; startRebind(action);}}
                    class="control_item">
                            
                    {#if waiting === action}
                        Press...
                    {:else}
                        {@html `${action}: <br><span class="control_value">${keyboardControl[action]}</span>`}
                    {/if}
                </div>
            {/each}
        </div>

        <input
            type="hidden"
            name="keyboard"
            value={JSON.stringify(keyboardControl)}
        />

        <h5>Gamepad</h5>

        <div class="controls_container">
                    
            {#each Object.keys(gamepadControl) as action, i}
                        
                <!-- svelte-ignore a11y_click_events_have_key_events -->
                <!-- svelte-ignore a11y_no_static_element_interactions -->
                <div
                    on:click={() => {activeControl = "gamepad"; startRebind(action);}}
                    class="control_item">
                            
                    {#if waiting === action}
                        Press...
                    {:else}
                        {@html `${action}: <br><span class="control_value">${gamepadControl[action]}</span>`}
                    {/if}
                </div>
            {/each}
        </div>

        <input
            type="hidden"
            name="gamepad"
            value={JSON.stringify(gamepadControl)}
        />
    </div>
</Component>