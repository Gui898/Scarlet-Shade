<script>

    import "$style/components/volumeStyle.css";

    import Component from "../Component.svelte";

    export let close;
    export let soundtrack;
    export let soundtrackVol;
    export let soundEffect;
    export let soundEffectVol;

    function soundtrackVolume(value) {
        soundtrack.volume = value;
    }

    function soundEffectVolume(value) {
        soundEffect.volume = value;
    }
</script>

<Component close={close} action="volume">
            
    <h2>Volume</h2>
            
    <label for="soundtrack">Soundtrack</label>
            
    <input
        type="range"
        name="soundtrack"
        id="soundtrack"
        min="0"
        max="1"
        step="0.01"
        bind:value={soundtrackVol}
        on:input={() => soundtrackVolume(soundtrackVol)}/>
            
    <label for="sound_effect">Sound Effect</label>
            
    <input
        type="range"
        name="sound_effect"
        id="sound_effect"
        min="0"
        max="1"
        step="0.01"
        bind:value={soundEffectVol}
        on:input={() => soundEffectVolume(soundEffectVol)}
    />
</Component>