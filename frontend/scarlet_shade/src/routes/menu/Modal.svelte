<script>
  export let open = false;
  export let close = () => {};
</script>

{#if open}
  <!-- ignorar warnings tudo bem -->
  <!-- svelte-ignore a11y_click_events_have_key_events -->
  <!-- svelte-ignore a11y_no_static_element_interactions -->
  <div class="overlay" on:click={close}></div>

  <div class="modal">
    <!-- Here comes the content -->
    <slot></slot>

    <button class="close" on:click={close}>
      Fechar
    </button>
  </div>
{/if}

<style>
  .overlay {
    position: fixed;
    inset: 0;
    background: rgba(0,0,0,0.6);
    backdrop-filter: blur(3px);
    z-index: 5;
  }

  .modal {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: white;
    padding: 20px;
    border-radius: 12px;
    min-width: 300px;
    z-index: 10; /* necessário ficar acima do overlay */
  }

  .close {
    margin-top: 15px;
  }
</style>
