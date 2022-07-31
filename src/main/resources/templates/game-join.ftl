<div class="content container">

    <p><i>Game Overview</i></p>
    <p>ID: ${model.game.id}</p>
    <p>Extension: ${model.game.extension?c}</p>
    <p>AI Speed: ${model.game.aiSpeed}</p>
    <p>Players: ${model.game.players?size}</p>
    <p>Started: ${model.game.hasStarted?c}</p>
    <p>Public: ${model.game.showPublic?c}</p>

    <form action="/.games/join?id=${model.game.id!}" method="post">
    <p>
        <label for="handle">Handle </label>
        <input type="text" name="handle" id="handle"/>
    </p>
    <button class="btn btn-primary" type="submit">Play a game</button>
    </form>

</div>