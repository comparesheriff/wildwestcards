<div class="content container">

    <p><i>Game Overview</i></p>
    <p>ID: ${model.game.id}</p>
    <p>Extension: ${model.game.extension?c}</p>
    <p>AI Speed: ${model.game.aiSpeed}</p>
    <p>Players:
        <ul>
        <#list model.game.players as player>
            <li>${player.handle!}</li>
        </#list>
        </ul>
    </p>
    <p>Started: ${model.game.hasStarted?c}</p>
    <p>Public: ${model.game.showPublic?c}</p>


</div>