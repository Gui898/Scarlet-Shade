
export function lerp(a, b, t) {
    
    if (t < 0 || t > 1) {
        throw new Error("Invalid smoothness value");
    }
    return a + (b - a) * t
}