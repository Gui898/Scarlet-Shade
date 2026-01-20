<script>
  import { enhance } from '$app/forms';
  import backImg from "$assets/icons/back.svg";
  import okImg from "$assets/icons/ok.svg";
  import "$style/components/componentStyle.css";

  export let action = "";

  export let open = false;
  export let close = () => {};
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
        
        <button type="button" class="close" on:click={close}>
          <img src={backImg} alt="Back" />
        </button>

        <button type="submit" class="save">
          <img src={okImg} alt="OK"/>
        </button>
      </div>
    </form>
  </div>
{/if}
