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
    const passwordRegex = /^(?=.*[a-z])(?=.*[A-Z]).{8,}$/; 

    let wrongUsername = false;
    let wrongEmail = false;
    let wrongPassword = false;

    function verifyRegex(regex, value, variable){
        if(!regex.test(value)){
            variable = true;
        }
    }
</script>

<Warning></Warning>
<form method="POST" action="?/register" class="options register">
    
    <input
        name="username"
        type="text"
        placeholder="Username"
        bind:value={username}
    />
    <input
        name="email"
        type="email"
        placeholder="Email"
        bind:value={email}
    />
    <input
        name="password"
        type="password"
        placeholder="Password"
        bind:value={password}
    />

    <button type="submit" class="button" onclick={() => playSound(gongSound)}>
        <img src={arrowButton} alt="" class="arrow" />
        Register
    </button>
</form>

{#if wrongUsername}
    <Warning>
        <h6>The name should be between 10 and 20 characters long</h6>
    </Warning>
{/if}

{#if wrongEmail}
    <Warning><h6>The name must have</h6></Warning>
{/if}

{#if wrongPassword}
    <Warning><h6>The password should be 8 characters long, at least 1 capital letter and 1 lowercase letter</h6></Warning>
{/if}