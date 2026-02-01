<script>
    
    import "$style/components/componentStyle.css";

    import { enhance, applyAction } from "$app/forms";
    import { invalidateAll } from "$app/navigation";

    import backImg from "$assets/icons/back.svg";
    import okImg from "$assets/icons/ok.svg";
    
    import apply from "$assets/soundEffect/apply.mp3";
    import closeEffect from "$assets/soundEffect/close.mp3";

    import { playSound } from "$script/utils/playSound.js";
    
    export let action = "";
    export let close = () => {};
    export let disabled = false;

    const handleClose = async () => {
        await applyAction({ type: 'success', status: 200 });
        close(); 
    };

    async function enhanceMethod(result, update) {
        
        if (result.type === "redirect") {
            window.location.href = result.location;
            return;
        }

        if (result.type === "failure") {
            await update();
        } 
        
        if (result.type === "success") {
            await update();
            await invalidateAll(); 
            close();
        }
    }
</script>

<!-- svelte-ignore a11y_click_events_have_key_events -->
<!-- svelte-ignore a11y_no_static_element_interactions -->
<div class="overlay" on:click={handleClose}></div>

<div class="modal">
        
    <form
        method="POST"
        action="?/{action}"
        use:enhance={action
            ? () => async ({ result, update }) => enhanceMethod(result, update) : undefined}
    >

        <slot></slot>

        <div class="buttons">
                
            <button type="button" class="close" 
                on:click={() => {handleClose(); playSound(closeEffect);}}>
                    
                <img src={backImg} alt="Back" />
            </button>

            <button type="submit" class="save" disabled={disabled}
                on:click={() => playSound(apply)}>
                    
                <img src={okImg} alt="OK" />
            </button>
        </div>
    </form>
</div>