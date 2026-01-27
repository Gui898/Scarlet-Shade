<script>
    
    import "$style/components/componentStyle.css";

    import { enhance } from "$app/forms";
    import { goto } from "$app/navigation";

    import backImg from "$assets/icons/back.svg";
    import okImg from "$assets/icons/ok.svg";
    
    import apply from "$assets/soundEffect/apply.mp3";
    import closeEffect from "$assets/soundEffect/close.mp3";

    import { playSound } from "$script/utils/playSound.js";
    
    export let action = "";
    export let close = () => {};

    function enhanceMethod(result) {
        
        if (result.type === "redirect") {
            goto(result.location);
            return;
        }
        if (result.type === "success") {
            close();
        }
    }

</script>

<!-- svelte-ignore a11y_click_events_have_key_events -->
<!-- svelte-ignore a11y_no_static_element_interactions -->
<div class="overlay" on:click={close}></div>

<div class="modal">
        
    <form
        method="POST"
        action="?/{action}"
        use:enhance={action
            ? () => async ({ result }) => enhanceMethod(result) : undefined}
    >

        <slot></slot>

        <div class="buttons">
                
            <button type="button" class="close" 
                on:click={() => {close(); playSound(closeEffect);}}>
                    
                <img src={backImg} alt="Back" />
            </button>

            <button type="submit" class="save" 
                on:click={() => playSound(apply)}>
                    
                <img src={okImg} alt="OK" />
            </button>
        </div>
    </form>
</div>