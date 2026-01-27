
export function playSound(sound, soundEffect = 0.3) {
        
    const cutSound = new Audio(sound);
    cutSound.volume = soundEffect;
    cutSound.play();
}