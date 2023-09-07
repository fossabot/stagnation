[![AGPL-3.0 License][license-shield]][license-url]

Official repository: [2009scape](https://gitlab.com/2009scape/2009scape).

I just add content for educational purposes and fun.<br>
If you want to play and don't know how, follow the instruction below.<br>
In the wiki tab you will find old changelogs.

<details><summary>Launching the game</summary>

### Installation process

***

- Make sure you have `java 11` installed before running the process. If you don't have, you can download it from [this page](https://adoptium.net/temurin/releases/?version=11).
- If you already have java installed or had it, you will have to download this archive. <br>At the top right of the page you will find a green button `code` click it, and then press the `Download ZIP` button.

![install_process_0](https://i.imgur.com/3RsP9EC.png)

- After download, go to the location where you saved the archive and then extract it, (_Where you want_).

***

- Ok, now we need to build the **server.jar**, in the first folder, find the `run.bat` and **click it**. <br>
- If you have properly installed java, you should see a cmd console, informing you that the process has been started.

![install_process_1](https://i.imgur.com/zxhUewI.png)

- This process takes _about 15 minutes_. After the whole procedure is completed,
- console should close automatically, if this does not happen, close it manually.

- Now we make sure that the process has finished correctly. 
- If so, in the server folder should be `server.jar` (If not, run `run.bat` again).

![install_process_2](https://i.imgur.com/wgxyxpz.png)


- To play you will need a **client**, unfortunately you will not find it here.
- I can only recommend you to download it from official repository [![download_button](https://i.imgur.com/rJsueNx.png)](https://gitlab.com/2009scape/rt4-client/-/artifacts)
- After you got it, move on. (_Btw: Client can be anywhere on the disk_).

### Launch process

***

- In **server** folder, find the `launch.bat`, run it, select by typing `1` and confirm with `eneter` Done. (_the first load takes some time_).

![run_process_2](https://i.imgur.com/OuB8R67.png)

- When the server is ready you will see such information:

![run_process_2](https://i.imgur.com/gGVZT3W.png)

- Now, run the **client**, and enjoy the game.

### Additional information

- If you want to change the default settings, edit the **default.conf** file. Can be found in the archive in the **worldprops** folder.
- If you want to give yourself the **administrator rights** (_or not_), edit config and change the `noauth_default_admin` from `false` to `true`, and save the changes.

![4](https://i.imgur.com/o0QNW56.png)

- If you made config changes while you had the server running, you will have to **re-run it**.

[![AGPL-3.0 License][license-shield]][license-url]

</details>

___

### License

Repository is under AGPL 3.0 license, you can read more [here](https://www.gnu.org/licenses/agpl-3.0.en.html).

[license-shield]: https://img.shields.io/badge/license-AGPL--3.0-informational
[license-url]: https://www.gnu.org/licenses/agpl-3.0.en.html