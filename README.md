<h1>Levantar api con java</h1>
    <ul>
        <li>Entrar en la carpeta: /API/target</li>
        <li> java -jar movie_api-0.0.1-SNAPSHOT.jar</li>
    </ul>
<h1>Levantar con Docker</h1>
    <ul>
        <li>lanzar los dos contenedores: docker compose up --build</li>
        <li>lanzar back y front separados:
        <ul>
            <li>back: docker compose up --build api</li>
            <li>front: docker compose up --build app</li>
        </ul>
        </li>
    </ul>
<h1>Levantar front con Node</h1>
    <p style="color: red">Asegurarse de levantar api primero</p>
    <ul>
        <li>Entrar en la carpeta: /APP</li>
        <li>npm run start</li>
    </ul>