<script>
  export let open = false;
  export let close = () => {};
</script>

{#if open}
  <!-- ignore warning -->
  <!-- svelte-ignore a11y_click_events_have_key_events -->
  <!-- svelte-ignore a11y_no_static_element_interactions -->
  <div class="overlay" on:click={close}></div>

  <div class="modal">
    <!-- Here comes the content -->
    <slot></slot>

    <div class="buttons">
      <button class="close" on:click={close}> Fechar </button>

      <button class="save"> Save </button>
    </div>
  </div>
{/if}

<style>

  @import "$style/style.css";

  .overlay {
    position: fixed;
    inset: 0;
    background: rgba(0, 0, 0, 0.6);
    backdrop-filter: blur(3px);
    z-index: 5;
  }

  .modal {
    display: flex;
    flex-direction: column;
    justify-content: start;
    align-items: center;
    gap: 20px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: var(--modal-menu-color);
    padding: 20px;
    border-radius: 12px;
    min-width: 300px;
    z-index: 10; /* necessário ficar acima do overlay */
  }

  .buttons {
    display: flex;
    justify-content: space-between;
    width: 100%;
    margin-top: 20px;
  }

  .close, .save {
    background-color: var(--secondary-color);
    color: white;
    font-size: 1.2rem;
    border-radius: 8px;
    font-weight: 600;
    padding: 10px 20px;
  }

  .save{
    background-color: var(--save-color);
  }

</style>
