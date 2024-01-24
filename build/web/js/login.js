/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
const input = document.querySelectorAll("input");
const notify = document.getElementById("notify");
for (var i = 0; i < input.length; i++)
    input[i].addEventListener("focus", () => {
        notify.style.display = "none";
    });

