<script>
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
    <slot></slot>

    <div class="buttons">
      <button class="close" on:click={close}>
        <img src={backImg} alt="Back" />
      </button>

      <form method="POST" action="?/{action}">
        <button class="save">
          <img src={okImg} alt="OK" />
        </button>
      </form>
    </div>
  </div>
{/if}
