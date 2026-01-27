<script>

    import Component from "../Component.svelte";

    export let close;
</script>

<Component close={close} action="deleteUser">
    <h5 class="delete_text">Are you sure you want to delete user?</h5>
</Component>