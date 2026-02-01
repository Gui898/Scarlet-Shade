<script>
    
    import { page } from '$app/stores';
    import { onDestroy } from "svelte";

    import "$style/components/configurationStyle.css";

    import Component from "../Component.svelte";
    import Warning from "../Warning.svelte";

    export let close;
    export let username;
    export let email;
    let password = "";

    const usernameRegex = /^[a-zA-Z0-9._-]{8,}$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$/;

    $: serverError = $page.form?.form === "configuration" ? $page.form.message : null;
    $: wrongUsername = username.length > 0 && !usernameRegex.test(username);
    $: wrongEmail = email.length > 0 && !emailRegex.test(email);
    $: wrongPassword = password.length > 0 && !passwordRegex.test(password);

    $: warningMessage = wrongUsername
        ? "The username must have at least 8 characters"
        : wrongEmail
        ? "The email must be in the correct format"
        : wrongPassword
        ? "The password must be at least 8 characters, with uppercase and lowercase letters"
        : serverError;

    $: isEmpty = username.length === 0 || email.length === 0;

    $: isButtonDisabled = wrongUsername || wrongEmail || wrongPassword || isEmpty;

    onDestroy(() => {
        if ($page.form) $page.form = null;
    });
</script>

<Component close={close} action="configuration" disabled={isButtonDisabled}>
    
    <h2>Configurations</h2>
    
    <div class="warning-slot">
        {#if warningMessage}
            <Warning styleClass="modal_error inline">
                <h6>{warningMessage}</h6>
            </Warning>
        {/if}
    </div>

    <div class="configurations">
                
        <input
            type="text"
            name="username"
            placeholder="Username"
            bind:value={username}/>
                
        <input
            type="text"
            name="email"
            placeholder="Email"
            bind:value={email}/>
                
        <input
            type="password"
            name="password"
            placeholder="Password"
            bind:value={password}/>       
    </div>
</Component>