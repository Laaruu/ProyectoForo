<%-- 
    Document   : index
    Created on : 02-nov-2012, 19:07:52
    Author     : vesprada
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="css/general.css" rel="stylesheet" type="text/css" />

        <title>Proyecto foro</title>

        <script>
            /* Con estas funciones validamos los campos */
            function vacio(q) { 
                for ( i = 0; i < q.length; i++ ) {  
                    if ( q.charAt(i) != " " ) {  
                        return true  
                    }  
                }  
                return false  
            }  
  
            function valida(F) {  
          
                if( vacio(F.usuario.value) == false ) {  
                    alert("Debes insertar tu usuario.")  
                    return false  
                } 
                
                if( vacio(F.password.value) == false ) {  
                    alert("Debes insertar tu contraseña.")  
                    return false  
                } else {
                    return true  
                }
          
            }
    
            /* Reloj analógico */

            //deteccion de capacidades del navegador
            var navok
            if ( document.getElementById || document.all )	
                navok = true
            else
                navok = false

            //fecha y hora actuales
            var ahora = new Date()

            //coordenadas posición numeros en el circulo del Inicializa
            var circulo_x = new Array()
            var circulo_y = new Array()
            circulo_x[7] = [80, 94, 100, 94, 77, 53, 28, 11,  5, 11, 28, 53]
            circulo_y[7] = [ 8, 26,  50, 74, 91, 97, 91, 74, 50, 26,  8,  2]

            //elementos graficos del Inicializa
            var horas = new Array()
            var minutos = new Array()
            var segundos = new Array()
            var numeros = new Array()

            function pinta_reloj() {
	
                //dibuja circulo del Inicializa
                var txt
                txt = '<div id="exterior" style="position:relative; width:100px; height:100px; visibility:visible">'
	
                for(i=1;i<=7;i++)	//minutero
                    txt += '<div id="min'+i+'" style="position:absolute; top:-100px; left:-100px; width:6px; height:6px; background-color:#FAF2CC; font-size:6px;"></div>'

                for(i=1;i<=5;i++)	//horas
                    txt += '<div id="hrs'+i+'" style="position:absolute; top:-100px; left:-100px; width:6px; height:6px; background-color:#FAF2CC; font-size:6px;"></div>'

                for(i=1;i<=7;i++)	//segundero
                    txt += '<div id="sec'+i+'" style="position:absolute; top:-100px; left:-100px; width:4px; height:4px; background-color:#FAF2CC; font-size:6px;"></div>'

                for(i=1;i<=12;i++)	//digitos
                    txt += '<div id="h'+i+'" style="position:absolute; top:-100px; left:0px; width:10px; height:10px; color:#FAF2CC; font-size:10pt; font-family: Comic Sans MS;">'+i+'</div>'

                txt += '</div>'

                document.write(txt)
                //	mueve_capa( exterior, 100, 200);
            }

            //movimiento agujas
            function avanza_reloj() {
                ahora = new Date()
                ahora.hrs = ahora.getHours()
                ahora.min = ahora.getMinutes()
                ahora.sec = ahora.getSeconds()
	
                ahora.hrs = ( ahora.hrs >= 12 ) ? ahora.hrs - 12 : ahora.hrs
	
                ahora.hrs = Math.floor( ( ahora.hrs * 5 ) + ( ahora.min/12 ) )
	
                for(n=0;n<horas.length;n++)
                    mueve_capa(horas[n], circulo_x[n][ahora.hrs], circulo_y[n][ahora.hrs])
                for(n=0;n<minutos.length;n++)
                    mueve_capa(minutos[n], circulo_x[n][ahora.min], circulo_y[n][ahora.min])
                for(n=0;n<segundos.length;n++)
                    mueve_capa(segundos[n], circulo_x[n][ahora.sec], circulo_y[n][ahora.sec])
            }

            function mueve_capa(id,x,y){
                id.style.left = x + 'px'
                id.style.top = y + 'px'
            }

            function obtiene_capa(idstr) {
                if (document.getElementById)
                    return document.getElementById(idstr)
                else if (document.all)
                    return document.all[idstr]
            }

            function Inicializa() {
	
                if ( !navok )
                    return
	
                //array de objetos aguja horas
                for( i=1; i<=5; i++)
                    horas[i-1] = obtiene_capa('hrs'+i)
                //array objetos minutero
                for( i=1; i<=7; i++)
                    minutos[i-1] = obtiene_capa('min'+i)
                //array objetos segundero
                for( i=1; i<=7; i++)
                    segundos[i-1] = obtiene_capa('sec'+i)
                //array objetos numeros circulo		
                for( i=1; i<=12; i++)
                    numeros[i] = obtiene_capa('h'+i)
  
                //inicializa posicion numeros circulo
                for( n=1; n<=12; n++)
                    mueve_capa(numeros[n], circulo_x[7][n-1], circulo_y[7][n-1])
  
                var centro = [0]
                var radio = 6
                var resultado = new Array()
                for( i=1; i<=60; i++) {
                    centro[i] = centro[i-1] + ((Math.PI*2)/60)
                    centro[i-1] += Math.PI/180
                }
  
                for( num=0; num<=6; num++) {
                    circulo_x[num] = new Array()
                    circulo_y[num] = new Array()
                    for( i=1; i<=60; i++) {
                        resultado = ((i-15)<0)? 60 + i - 15 : i - 15
                        circulo_x[num][i] = Math.floor((num*radio*Math.cos(centro[resultado]))+56)
                        circulo_y[num][i] = Math.floor((num*radio*Math.sin(centro[resultado]))+55)
                    }
                    circulo_x[num][0]=circulo_x[num][60]
                    circulo_y[num][0]=circulo_y[num][60]
                }
  
                var IdIntervalo = setInterval("avanza_reloj()", 100)
            }

            window.onload = Inicializa
        </script>
    </head>
    <body>
        <div class="cabecera">

            <div class="span5">
                <img src="img/logo.png" />
            </div>
            <div class="reloj">
            <script type="text/javascript" >
                pinta_reloj()
            </script>
            </div>
            <div class="titulo" class="offset4 span6">
                <span>Aula Virtual</span>
            </div>
        </div>

        <form action="Login" method="post" onSubmit="return valida(this);">
            <div id="cuerpo" class="span3">
                <fieldset>
                    <div>
                        Usuario: <input type="text" name="usuario" />
                    </div>
                    <div>
                        Password: <input type="password" name="password" />
                    </div>
                    <div>
                        <input type="submit" value="Iniciar sesión" />
                    </div>
                </fieldset>
            </div>
        </form>

        <div>
            <img src="img/ies1.jpg" id="imagen"/>	
        </div>

    </body>
</html>