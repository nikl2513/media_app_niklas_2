package com.example.mediaappniklas2

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Key
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mediaappniklas2.navcontroller.Screen
@Composable
fun SignupForm(modifier: Modifier = Modifier, navController: NavController) {
    Surface {

        var credentials by remember { mutableStateOf(SignupCredentials()) }
        val context = LocalContext.current
        Image(
            painter = painterResource(id = R.drawable.background1),
            contentDescription = "",
            modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp)
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "")
            SignupField(
                value = credentials.login,
                onChange = {data -> credentials = credentials.copy(login = data) },
                modifier = Modifier.fillMaxWidth()
            )
            SignupPasswordField(
                value = credentials.pwd,
                onChange =  {data -> credentials = credentials.copy(pwd = data) },
                submit = { SignupcheckCredentials(credentials, context) },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            SignupLabeledCheckbox(
                label = "Remember Me",
                onCheckChanged = {
                    credentials = credentials.copy(remember = !credentials.remember)},
                isChecked = credentials.remember
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {navController.navigate(Screen.GradientButton.route)},
                enabled = credentials.isNotEmpty(),
                shape = RoundedCornerShape(5.dp),
                modifier = Modifier.fillMaxWidth()

            )
            {
                Text("Login")

            }
        }
    }
}



fun SignupcheckCredentials(creds: SignupCredentials, context: Context){
    if(creds.isNotEmpty() && creds.login == "admin"){
        context.startActivity(Intent(context, MainActivity::class.java))
        (context as Activity).finish()
    }else{
        Toast.makeText(context,"wrong Credentials", Toast.LENGTH_SHORT).show()
    }
}
data class SignupCredentials(var login : String = "", var pwd : String = "", var remember : Boolean = false){
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && pwd.isNotEmpty()
    }
}

@Composable
fun SignupLabeledCheckbox(
    label : String,
    onCheckChanged : () -> Unit,
    isChecked : Boolean
){

    Row(
        Modifier
            .clickable(
                onClick = onCheckChanged
            )
            .padding(4.dp)
    ) {
        Checkbox(checked = isChecked, onCheckedChange = null)
        Spacer(Modifier.size(6.dp))
        Text(label)

    }
}





@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupField(
    value : String,
    onChange : (String)->Unit,
    modifier: Modifier = Modifier,
    label : String = "Signup",
    placeholder : String = "Enter your username"
){

    val focusManager = LocalFocusManager.current
    val leadingIcon = @Composable{
        Icon(
            Icons.Default.Person,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }

    TextField(
        value =value,
        onValueChange =onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
        keyboardActions = KeyboardActions(
            onNext = { focusManager.moveFocus(FocusDirection.Down)  }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = VisualTransformation.None
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupPasswordField(
    value : String,
    onChange : (String)->Unit,
    submit : ()->Unit,
    modifier: Modifier = Modifier,
    label : String = "Password",
    placeholder : String = "Enter your Password"
){

    var isPasswordVisible by remember { mutableStateOf(false) }

    val leadingIcon = @Composable{
        Icon(
            Icons.Default.Key,
            contentDescription = "",
            tint = MaterialTheme.colorScheme.primary
        )
    }
    val trailingIcon = @Composable{
        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
            Icon(
                if(isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                contentDescription = "",
                tint = MaterialTheme.colorScheme.primary
            )
        }

    }



    TextField(
        value =value,
        onValueChange =onChange,
        modifier = modifier,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
        keyboardActions = KeyboardActions(
            onDone = { submit()  }
        ),
        placeholder = { Text(placeholder) },
        label = { Text(label) },
        singleLine = true,
        visualTransformation = if(isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation()
    )
}

/*@Preview(showBackground = true, device = "id:Nexus One", showSystemUi = true)
@Composable
fun loginPreviewdark() {
    MediaAppNiklas2Theme(darkTheme = true) {
        SignupForm()
    }

}*/
