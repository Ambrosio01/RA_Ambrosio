package com.example.ra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ra.ui.theme.RATheme



import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember


import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val objeto_model : Model by viewModels()
        setContent {
            RATheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var text = remember { mutableStateOf(value = "") }
                    var text2 = remember { mutableStateOf(0.0) }
                    var text3 = remember { mutableStateOf(value = 0.0)}

                    var desc = remember { mutableStateOf(value = "")}
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
//Image(
    //painterResource(id = R.drawable.img3),
  //  contentDescription = "img prueva",
   // modifier = Modifier
     //   .padding(0.dp)
        //.width(300.dp)


//)
                        Text(text = "COTIZADOR DE AUTOS NUEVOS")
                        Row(

                        ) {
                            Text(text = "Nombre")
                            Spacer(modifier = Modifier.size(35.dp))
                            TextField(
                                value = desc.value ,
                                modifier = Modifier.size(240.dp,50.dp),
                                onValueChange ={
                                    desc.value = it

                                } )
                            objeto_model.asignarNombre(desc.value.toString())
                        }


                        Row {


                            Text(text = "Marca")
                            Spacer(modifier = Modifier.padding(80.dp,0.dp))
                            generarSpinner(objeto_model)

                        }

                        Row {
                            Text(text = "Enganche")
                            Spacer(modifier = Modifier.padding(60.dp,0.dp))
                            generarSpinner1(objeto_model)

                        }

                        Row {
                            Text(text = "Financiamiento")
                            Spacer(modifier = Modifier.padding(20.dp,0.dp))
                            generarSpiner2(objeto_model,text3.value.toString() )

                        }
                        var texSalida =remember { mutableStateOf(value = "")}
                        Button(onClick = {
                            texSalida.value = objeto_model.generarFinanciamiento()

                        },
                            colors = ButtonDefaults.buttonColors(Color.Red),
                            modifier = Modifier
                                .width(300.dp)
                                .height(45.dp)

                        ) {
                            Text(text = "Cotizar")
                        }

                        Column(
                            modifier=Modifier.shadow(1.dp)
                                
                        ) {
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(text = texSalida.value )

                        }


                    }

                }
            }


        }
    }
}


@Composable
fun generarSpinner(objeto_car: Model)
{
    var expanded by remember { mutableStateOf(false) }

    val suggestions = arrayOf(
        arrayOf(0,"Honda Acord $678,026.22","Honda Acord",678026.22,),
        arrayOf(0,"VW Touareg $879,266.15","VW Touareg",879266.15,),
        arrayOf(0,"BMW X5  $1,125,366.87","BMW X5",11253666.87,),
        arrayOf(0,"Mazda Cx7 $988,641.02","Mazda Cx7",988641.02,)
    )

    Box() {
        Button(onClick = { expanded = !expanded }
      //  ,   colors = ButtonDefaults.buttonColors(Color.Green),
        ){
            Text ("Opciones")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = null,
            )
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    expanded = false

                    objeto_car.asignarMarca(label [2] as String,label [3] as Double)

                }) {
                    Text(text = label [1].toString())
                    //Text(text = label.toString())
                }
            }
        }

    }
}



@Composable
fun generarSpinner1(objeto_Eganche:Model)
{
    var spiner2 by remember { mutableStateOf(false) }
    //val opcion = listOf("20%","40%","60")
    val opcion = arrayOf(
        arrayOf(0,"20%",20),
        arrayOf(0,"40%",40),
        arrayOf(0,"60",60)
    )

    Box() {
        Button(onClick = {spiner2=!spiner2 }
               ) {
            Text(text = "Seleccione")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription =null
            )
            DropdownMenu(
                expanded = spiner2,
                onDismissRequest = { spiner2 = false },
            )
            {
                opcion.forEach{label2 ->
                    DropdownMenuItem(onClick = {
                        spiner2 = false

                        objeto_Eganche.asignarEnganche(label2[2].toString().toDouble())


                    }) {

                        Text(text = label2[1].toString())
                    }

                }
            }
        }
    }
}

@Composable
fun generarSpiner2(objeto_finan:Model,texto3: String)
{
    var Spiner3 by remember { mutableStateOf(false) }
    val elejir = arrayOf(
        arrayOf(0,"1 a??o 7,5%",1,7.5,),
        arrayOf(0,"2 aa??os 9.5%",2,9.5,),
        arrayOf(0,"3 a??os 10.3%",3,10.3,),
        arrayOf(0,"4 a??os 12.6%",4,12.6,),
        arrayOf(0,"5 a??os 13.5%",5,13.5,))

    Box() {
        Button(onClick = { Spiner3 =! Spiner3}
       ) {
            Text(text = "Seleccione plazo")
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription =null
            )
            DropdownMenu(
                expanded = Spiner3,
                onDismissRequest = { Spiner3=false })
            {
                elejir.forEach { label3 ->
                    DropdownMenuItem(onClick = {
                        Spiner3 = false

                        objeto_finan.asignarFinanciamiento(label3[2] as Int, label3[3] as Double)

                    })

                    {
                        Text(text = label3[1].toString())
                    }

                }
            }
        }
    }
}




