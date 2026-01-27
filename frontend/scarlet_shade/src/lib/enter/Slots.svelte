<script>

    import "$style/pages/menu.css";

    export let slots;
    export let trash;
</script>

{#each Array(4) as slot, i}
            
    <div class="slot_container">
                
        <h4 class="slot_title">Slot {i + 1}</h4>
                    
        {#if slots[i] != null && slots[i].gameCompleted} 
            
            <form method="POST" action="?/getSlot">  
                        
                <!-- svelte-ignore a11y_consider_explicit_label -->
                <button class="slot_style completed"></button>

                <input type="hidden" name="numberSlot" value="{i + 1}">
            </form>  
        {:else if slots[i] != null}
                    
            <form method="POST" action="?/getSlot">  
                        
                <!-- svelte-ignore a11y_consider_explicit_label -->
                <button class="slot_style started"></button>

                <input type="hidden" name="numberSlot" value="{i + 1}">
            </form>
        {:else}
                    
            <form method="POST" action="?/createSlot">  
                
                <!-- svelte-ignore a11y_consider_explicit_label -->
                <button class="slot_style notStarted"></button>

                <input type="hidden" name="numberSlot" value="{i + 1}">
            </form>
        {/if}                    
                
        <form method="POST" action="?/deleteSlot">
                    
            <button class="trash">
                        
                <img src={trash} alt="" />
            </button>
                    
            <input type="hidden" name="numberSlot" value="{i + 1}">
        </form>
    </div>
{/each}