//variables//
const btn=document.querySelector("#btn-menu");
const btnNavBar=document.querySelector(".barra-nav");
const btnBgNav=document.querySelector("#menu-mobile-bg");
const headerContainer = document.querySelector(".header-container");
const menuItemBtn = document.querySelector(".item-btn");

let scroll_position = 0;

window.addEventListener('scroll', (e)=> {
    if(this.window.scrollY > 50){
        headerContainer.classList.add("header-position")
    }
    if(this.window.scrollY < 50){
        headerContainer.classList.remove("header-position")
    }
})

btn.addEventListener("click",openMenu)
//btnBgNav.addEventListener("click",closeMenu);

function openMenu(){
    btnNavBar.classList.add("active-menu")
}
function closeMenu(){
    btnNavBar.classList.remove("active-menu")
}
