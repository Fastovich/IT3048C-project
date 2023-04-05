package com.example.placefinder

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.PopupProperties
import com.example.placefinder.dto.Park
import com.example.placefinder.ui.theme.PlaceFinderTheme
import com.google.android.gms.identity.intents.Address
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModel<MainViewModel>()

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                viewModel.fetchParks()
                val parks by viewModel.parks.observeAsState(initial = emptyList<Address>())
                PlaceFinderTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                        Greeting("Android")
                    }
                    Parks(parks as List<Address>)
                    TextFieldWithDropdownUsage(parks as List<Address>)
                    ButtonBar()
                }
        }
    }
}
    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }
    
    @Composable
    fun ButtonBar() {
        Row(modifier = Modifier.padding(all = 2.dp)) {
        }

        val context = LocalContext.current
        Button(
            onClick = {
                Toast.makeText(context, "Selected State $strSelectedPark", Toast.LENGTH_LONG).show()
            },
            modifier = Modifier.padding(all = Dp( 10F)),
            enabled = true,
            border = BorderStroke(width = 1.dp, brush = SolidColor(Color.Green)),
            shape = MaterialTheme.shapes.medium
        ){
            Text(text = "Sample", color = Color.White)
        }
    }
    
    @Composable
    fun Parks(parksIn: List<Address>) {
        var states : String by remember { mutableStateOf("OH") }
        var expanded by remember { mutableStateOf(false) }

        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            Row(
                Modifier
                    .padding(24.dp)
                    .clickable {
                        expanded = !expanded
                    }
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = states, fontSize = 18.sp, modifier = Modifier.padding(end = 8.dp))
                Icon(imageVector = Icons.Filled.ArrowDropDown, contentDescription = "")
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    parksIn.forEach {
                        park -> DropdownMenuItem(onClick = {
                            expanded = false
                            states = park.toString()
                    }) {
                        Text(text = park.toString())
                    } 
                    }
                }
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        PlaceFinderTheme {
            Column {
                Greeting("Android")
                ButtonBar()
            }
        }
}

    var strSelectedPark = "no State Selected"
    var selectedPark = Park.Datum.Address(stateCode = "", city = "")

    @Composable
    fun TextFieldWithDropdownUsage(parksIn: List<Address>){
        val dropDownOptions = remember { mutableStateOf(listOf<Address>()) }
        val textFieldValue = remember { mutableStateOf(TextFieldValue()) }
        val dropDownExpanded = remember { mutableStateOf(false) }
        
        fun onDropdownDismissRequest(){
            dropDownExpanded.value = false
        }
        
        fun onValueChanged(value: TextFieldValue) {
            strSelectedPark = value.text
            dropDownExpanded.value = true
            textFieldValue.value = value
            dropDownOptions.value = parksIn.filter { it.toString().startsWith(value.text) && it.toString() != value.text }.take(3)
        }
        
        TextFieldWithDropdown(
            modifier = Modifier.fillMaxWidth(),
            value = textFieldValue.value,
            setValue = ::onValueChanged,
            onDismissRequest = ::onDropdownDismissRequest,
            dropDownExpanded = dropDownExpanded.value,
            list = dropDownOptions.value,
            label = "Park"
        )
    }

    @Composable
    fun TextFieldWithDropdown(
        modifier: Modifier = Modifier,
        value: TextFieldValue,
        setValue: (TextFieldValue) -> Unit,
        onDismissRequest: () -> Unit,
        dropDownExpanded: Boolean,
        list: List<Address>,
        label: String = ""
    ){
        Box(modifier) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .onFocusChanged { focusState ->
                        if (!focusState.isFocused)
                            onDismissRequest()
                    },
                value = value,
                onValueChange = setValue,
                label = { Text(label) },
                colors = TextFieldDefaults.outlinedTextFieldColors()
            )
            DropdownMenu(
                expanded = dropDownExpanded,
                properties = PopupProperties(
                    focusable = false,
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true
                ),
                onDismissRequest = onDismissRequest
            ) {
                list.forEach { text -> 
                    DropdownMenuItem(onClick = {
                        setValue(
                            TextFieldValue(
                                text.toString(),
                                TextRange(text.toString().length)
                            )
                        )
                    }) {
                        Text(text = text.toString())
                    }
                }
                
            }
        }
    }