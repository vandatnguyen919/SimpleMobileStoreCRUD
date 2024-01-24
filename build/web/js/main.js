/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
document.getElementById('profileButton').addEventListener('click', function () {
    var menu = document.getElementById('profileMenu');
    menu.style.display = (menu.style.display === 'none' || menu.style.display === '') ? 'block' : 'none';
});

// Close the menu if the user clicks outside of it
window.addEventListener('click', function (event) {
    var menu = document.getElementById('profileMenu');
    if (event.target != document.getElementById('profileButton') && event.target != menu && !menu.contains(event.target)) {
        menu.style.display = 'none';
    }
});

