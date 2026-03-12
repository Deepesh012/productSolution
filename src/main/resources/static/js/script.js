document.addEventListener('DOMContentLoaded', function () {
    const sidebar = document.querySelector('.sidebar');
    const content = document.querySelector('.content');
    const hamburger = document.querySelector('.fa-bars');

    // Toggle sidebar
    hamburger.addEventListener('click', function () {
        if (window.innerWidth > 768) {
            // Desktop toggle
            sidebar.classList.toggle('hide');
            content.classList.toggle('full');
        } else {
            // Mobile toggle
            sidebar.classList.toggle('show');
        }
    });
	
	

    // Toggle submenus
    const menuItems = document.querySelectorAll('.sidebar-menu .nav-item > a');

    menuItems.forEach(function(item){
        const submenu = item.nextElementSibling;
        if(submenu && submenu.classList.contains('nav-treeview')){
            item.addEventListener('click', function(e){
                e.preventDefault();
                submenu.style.display = (submenu.style.display === 'block') ? 'none' : 'block';
            });
        }
    });
});
