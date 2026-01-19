<script>
  import backImg from "$assets/icons/back.svg";
  import okImg from "$assets/icons/ok.svg";

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
      <button class="close" on:click={close}> 
        <img src={backImg} alt="Back" />
      </button>

      <button class="save">
        <img src={okImg} alt="OK" />
      </button>
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
    color: var(--text-modal-color);
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
    margin-left: 0;
  }

  img {
    padding: 0 !important;
  }

</style>
