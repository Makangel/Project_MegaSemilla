//variables

const cantidadSelector=document.querySelector("#cantidad");
const monto = document.querySelector("#monto");


//evento q escucha cuando la pag carga

document.addEventListener("DOMContentLoaded", ()=>{
    console.log("pagina cargada")
    precio=250*1100/1000
    monto.textContent=precio;
})

//evento q escucha cuando cambia el selec de cantidad

cantidadSelector.addEventListener("change",(event)=>{
    console.log(event.target.value)
    let valor = event.target.value
    precio=valor*1100/1000
    monto.textContent=precio;
})
