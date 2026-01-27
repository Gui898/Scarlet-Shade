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