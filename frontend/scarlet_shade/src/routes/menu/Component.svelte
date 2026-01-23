<script>
  import { enhance } from '$app/forms';
  import backImg from "$assets/icons/back.svg";
  import okImg from "$assets/icons/ok.svg";
  import "$style/components/componentStyle.css";

  import apply from "$assets/soundEffect/apply.mp3";
  import closeEffect from "$assets/soundEffect/close.mp3";

  export let action = "";

  export let open = false;
  export let close = () => {};

  function playSound(sound){
    const effect = new Audio(sound)
    effect.volume = 0.2;
    effect.play();
  }
</script>

{#if open}
  <!-- svelte-ignore a11y_click_events_have_key_events -->
  <!-- svelte-ignore a11y_no_static_element_interactions -->
  <div class="overlay" on:click={close}></div>

  <div class="modal">
    <form method="POST" action="?/{action}" 
      use:enhance={() => {
        return async ({ result }) => {
          if (result.type === 'success' || result.type === 'redirect') {
            close(); 
          }
        };
      }}>
      
      <slot></slot>

      <div class="buttons">
        
        <button type="button" class="close" on:click={() => {close(); playSound(closeEffect)}}>
          <img src={backImg} alt="Back" />
        </button>

        <button type="submit" class="save" on:click={() => playSound(apply)}>
          <img src={okImg} alt="OK"/>
        </button>
      </div>
    </form>
  </div>
{/if}
