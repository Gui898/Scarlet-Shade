<script>
    
    import "$style/components/configurationStyle.css";

    import Component from "../Component.svelte";

    export let close;
    export let username;
    export let email;
</script>

<Component close={close} action="configuration">
    
    <h2>Configurations</h2>
            
    <div class="configurations">
                
        <input
            type="text"
            name="username"
            placeholder="Username"
            value={username}/>
                
        <input
            type="text"
            name="email"
            placeholder="Email"
            value={email}/>
                
        <input
            type="text"
            name="password"
            placeholder="Password"
            value=""/>       
    </div>
</Component>