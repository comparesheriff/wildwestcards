<div class="content container">

    <h1>HELLO ${model.player.handle!""}<#if model.player.host!false> - HOST</#if></h1>

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

    <button class="btn-primary" onclick="copyGameLink('${model.link!}')">Copy Join Game Link</button>

    <script>
        function copyGameLink(link) {
            if (link) {
                navigator.clipboard.writeText(link);
                alert("Join Link Copied To Clipboard");
            }
        }
    </script>
    <#if ((model.player.host)!false) && ((model.game.canStart())!false)>
        <a href="/game/start?id=${model.game.id!""}" class="btn-primary">
            START GAME!
        </a>
    </#if>
</div>