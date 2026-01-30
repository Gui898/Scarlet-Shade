<script>
    import "$style/pages/homeStyle.css";
    import "$style/components/buttonStyle.css";

    import arrowButton from "$assets/images/arrowButtonImage.png";
    import gongSound from "$assets/soundEffect/gong.mp3";
    
    import { playSound } from "$script/utils/playSound.js";
    import { enhance } from "$app/forms";
    import Warning from "../Warning.svelte";

    export let username = '';
    export let password = '';

    export let form;

    $: isFormValid = username.length == 0 || password.length == 0;

    function fadeScale(node, { delay = 0, duration = 400, start = 0.6 }) {
        return {
            delay,
            duration,
            css: (t) => `
            opacity: ${t};
            transform: scale(${start + (1 - start) * t});
        `,
        };
    }
</script>

<form
    transition:fadeScale={{ duration: 400, delay: 600 }}
    method="POST"
    action="?/login"
    class="options login"
    use:enhance
>
    <input
        name="username"
        type="text"
        autocomplete="username"
        placeholder="Username"
        bind:value={username}
    />

    <input
        name="password"
        type="password"
        autocomplete="current-password"
        placeholder="Password"
        bind:value={password}
    />

    <button type="submit" class="button" onclick={() => playSound(gongSound)} disabled={isFormValid}>
        <img src={arrowButton} alt="" class="arrow" />
        Login
    </button>
</form>

{#if form?.error && form?.form == "login"}
    <Warning><h6>{form.message}</h6></Warning>
{/if}