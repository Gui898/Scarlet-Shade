<script>
    import "$style/pages/homeStyle.css";
    import "$style/components/buttonStyle.css";

    import arrowButton from "$assets/images/arrowButtonImage.png";
    import gongSound from "$assets/soundEffect/gong.mp3";
    import Warning from "../Warning.svelte";

    import { playSound } from "$script/utils/playSound.js";

    export let username;
    export let email;
    export let password;

    const usernameRegex = /^[a-zA-Z0-9._-]{8,}$/;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$/;

    $: wrongUsername = username.length > 0 && !usernameRegex.test(username);
    $: wrongEmail = email.length > 0 && !emailRegex.test(email);
    $: wrongPassword = password.length > 0 && !passwordRegex.test(password);

    $: warningMessage = wrongUsername
        ? "The username must have at least 8 characters"
        : wrongEmail
        ? "The email must be in the correct format"
        : wrongPassword
        ? "The password must be at least 8 characters, with uppercase and lowercase letters"
        : null;

    $: isFormValid =
        wrongPassword ||
        wrongEmail ||
        wrongUsername ||
        username.length == 0 ||
        email.length == 0 ||
        password.length == 0;
</script>

<Warning></Warning>
<form method="POST" action="?/register" class="options register">
    <input
        name="username"
        type="text"
        placeholder="Username"
        bind:value={username}
    />
    <input name="email" type="email" placeholder="Email" bind:value={email} />
    <input
        name="password"
        type="password"
        placeholder="Password"
        bind:value={password}
    />

    <button
        type="submit"
        class="button"
        onclick={() => playSound(gongSound)}
        disabled={isFormValid}
    >
        <img src={arrowButton} alt="" class="arrow" />
        Register
    </button>
</form>

{#if warningMessage}
    <Warning>
        <h6>{warningMessage}</h6>
    </Warning>
{/if}

