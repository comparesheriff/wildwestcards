<div class="home-content container">

    <p><i>Wild West Card Game</i></p>

    <div id="gameSetup"></div>
    <div class="container mb-2">
        <h5>Current Games:</h5>
        <#if (model.games![])?size <= 0>
            <p class="text-danger">No Current Games Found</p>
        </#if>
        <#list model.games as game>
            <#if game.showPublic && !game.hasStarted>
                <p>Extension: ${game.extension?c}<br>
                   AI Speed: ${game.aiSpeed}<br>
                   Players: ${game.players?size}<#if game.isFull()> - FULL!</#if>
                </p>
                <#if !game.isFull()><a href="/game/join?id=${game.id}">JOIN</a></#if>
            </#if>
        </#list>
    </div>

    <div id="gameState"></div>
    <div id="gameInfo" class="mt-5">
        <form action="/.games/add" method="post">
        <p>
            <label for="isPublic">Public Game:</label>
            <input type="checkbox" id="isPublic" name="isPublic" value="true"/>
        </p>
        <p>
            <fieldset>
                <legend>Select an AI Speed:</legend>

                <div>
                    <input type="radio" id="aiSpeedInstant" name="aiSpeed" value="INSTANT"/>
                    <label for="aiSpeedInstant">Instant</label>
                </div>

                <div>
                    <input type="radio" id="aiSpeedFast" name="aiSpeed" value="FAST" checked="checked"/>
                    <label for="aiSpeedFast">Fast</label>
                </div>

                <div>
                    <input type="radio" id="aiSpeedSlow" name="aiSpeed" value="SLOW"/>
                    <label for="aiSpeedSlow">Slow</label>
                </div>
            </fieldset>
        </p>
        <p>
            <label for="useExtension"> Expansion Sidestep Township - 4-8 players (Beta):</label>
            <input type="checkbox" id="useExtension" name="useExtension" value="true"/>
        </p>
        <p>
            <label for="handle">Handle </label>
            <input type="text" name="handle" id="handle"/>
        </p>
            <button class="btn btn-primary" type="submit">Play a game</button>
        </form>
    </div>
    <div>
        <div>
            <div id="chatLog"></div>
            <div id="chatNames"></div>
        </div>
        <div id="chatInputDiv">
            <!--            refactor: what is this?-->
            <form id="chatInputForm">
                <label for="chatinput">Chat</label>
                <input type="text" id="chatinput">
                <input id="chatInputSubmit" type="button" value="Send">
            </form>
        </div>
        <div>
            <label for="gameLog">GameLog and Chat</label>
            <textarea id="gameLog" rows='10' cols='60' style="display:none;" readonly></textarea>
        </div>
        <div id="gameMessages"></div>
        <div id="remainingroles"></div>
    </div>
</div>