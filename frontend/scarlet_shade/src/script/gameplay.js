import { Enemy } from "./models/enemies/Enemy.js";
import { Camera } from "./models/player/Camera.js";
import { Player } from "./models/player/Player.js";
import { GameObject } from "./models/GameObject.js";
import { InputManager } from "./engine/InputManager.js";
import { onMount } from "svelte";
import { EntityManager } from "./engine/EntityManager.js";
import { WorldManager } from "./engine/WorldManager.js";
import { RenderWorld } from "./world/RenderWorld.js";

let inputManager;
let worldManager;
let renderWorld;

export function startGame(canvas) {

    canvas.width = window.innerWidth;
    canvas.height = window.innerHeight;

    addEventListener('resize', () => {
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
    });

    const ctx = canvas.getContext("2d");

    inputManager = new InputManager(canvas);
    worldManager = new WorldManager();
    renderWorld = new RenderWorld();

    EntityManager.init(new Player(48, 48, 3530, 3000, 5), new Camera(canvas, 0, 0));

    gameloop(canvas, ctx);
}

const gameloop = (canvas, ctx) => {
    
    ctx.clearRect(0, 0, canvas.width, canvas.height);

    const player = EntityManager.entities.player;
    const camera = EntityManager.entities.camera;

    camera.updateCamera(player);
    worldManager.checkConnection(player);
    inputManager.applyInputs(player, worldManager);
    
    ctx.save();
    
    EntityManager.drawEntities(ctx, renderWorld, worldManager);
    
    ctx.restore();

    requestAnimationFrame(() => gameloop(canvas, ctx));
}