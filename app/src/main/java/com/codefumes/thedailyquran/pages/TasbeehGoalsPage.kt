package com.codefumes.thedailyquran.pages

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import android.widget.Toast
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.codefumes.thedailyquran.ui.theme.*
import com.codefumes.thedailyquran.layout.FABLayout
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.em
import androidx.navigation.NavHostController
import com.codefumes.thedailyquran.DBHelpers.TasbeehGoalDB
import com.codefumes.thedailyquran.R
import com.codefumes.thedailyquran.components.TasbeehGoal
import com.codefumes.thedailyquran.models.TasbeehGoal
import com.codefumes.thedailyquran.schemas.Contracts

@ExperimentalMaterial3Api
@Composable
fun TasbeehPage(modifier: Modifier = Modifier, navController: NavHostController) {
    val (currentGoalState, setCurrentGoalState) = remember { mutableStateOf(-1) }
    val openDialog = remember { mutableStateOf(false) }

    val context = LocalContext.current

    val goals = remember {
        generateList(context = context)
    }
    if (goals.size > 0 && goals[0].active == 1) {
        setCurrentGoalState(0);
    }
    CreateGoalDialog(openDialog = openDialog, goals)

    FABLayout(fab = {
        ExtendedFloatingActionButton(
            onClick = { openDialog.value = true; },
            icon = { Icon(Icons.Filled.Add, "Localized description") },
            text = { Text(text = "Create Goal") },
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    }, navController = navController, content = {
        Column(
        ) {
            Surface(
                color = orange1,
                shape = RoundedCornerShape(15.dp),
                shadowElevation = 4.dp,
                contentColor = white,
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    skin1,
                                    skin3
                                )
                            )
                        )
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(15.dp),
                        color = Color.Transparent
                    ) {
                        Column() {
//                            Column(
//                            ) {
//                                Text(
//                                    text = "Tasbeeh Goals",
//                                    style = MaterialTheme.typography.headlineLarge
//                                )
//                            }
//                            Spacer(modifier.size(20.dp))
//                            Column() {
//                                Text(
//                                    text = "Your Current Goal:",
//                                    style = MaterialTheme.typography.headlineSmall
//                                )
//                            }
                            Row(
                                modifier= Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Column() {
                                    Text(
                                        text = "Tasbeeh Goals",
                                        style = MaterialTheme.typography.headlineLarge
                                    )
                                    Spacer(modifier.size(20.dp))
                                    Text(
                                        text = "Your Current Goal:",
                                        style = MaterialTheme.typography.headlineSmall
                                    )
                                }
                                Column() {
                                    Image(
                                        painterResource(id = R.drawable.tasbih_card_img),
                                        contentDescription = "quran_icon",
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally)
                                            .size(100.dp)
                                    )
                                }
                            }
                            Spacer(modifier.size(20.dp))
                            if (currentGoalState == 0) {
                                Text(
                                    text = when (currentGoalState) {
                                        0 -> goals[currentGoalState].dua
                                        else -> "No Goals Set"
                                    },
                                    fontSize = 8.em,
                                    fontFamily = NooreHudaFont,
                                    lineHeight = 1.5.em,
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.align(Alignment.End)
                                )
                                Spacer(modifier.size(20.dp))
                                LinearProgressIndicator(
                                    progress = goals[currentGoalState].getCurrProgress(),
                                    modifier = Modifier.align(Alignment.End),
                                    color = Color.White,
                                    backgroundColor = Color.Transparent.copy(alpha = 0.1f)
                                )
                            } else {
                                Text(
                                    text = "No Goals Set",
                                    textAlign = TextAlign.End,
                                    modifier = Modifier.align(Alignment.End),
                                    fontSize = 6.em
                                )
                            }
                        }
                    }
                }
            }
            Spacer(modifier.size(20.dp))
            if (goals.size > 0) {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    items(goals.size) { index ->
                        TasbeehGoal(
                            tasbeehGoal = goals[index],
                            navController = navController
                        )
                    }
                    item() {
                        Spacer(modifier.size(80.dp))
                    }
                }
            } else {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "No goals. Add a new goal by clicking on the button below",
                        style = MaterialTheme.typography.headlineLarge,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    })
}

fun generateList(context: Context): SnapshotStateList<TasbeehGoal> {
    val dbhelper = TasbeehGoalDB(context = context);
    val db = dbhelper.readableDatabase;
    val sortOrder = "${Contracts.TasbeehGoalEntry.COLUMN_NAME_ACTIVE} DESC"
    val cursor = db.query(
        Contracts.TasbeehGoalEntry.TABLE_NAME,   // The table to query
        null,             // The array of columns to return (pass null to get all)
        null,              // The columns for the WHERE clause
        null,          // The values for the WHERE clause
        null,                   // don't group the rows
        null,                   // don't filter by row groups
        sortOrder               // The sort order
    );
    val items = mutableStateListOf<TasbeehGoal>()
    with(cursor) {
        while (moveToNext()) {
            val itemId = getInt(getColumnIndexOrThrow(BaseColumns._ID))
            val itemTitle =
                getString(getColumnIndexOrThrow(Contracts.TasbeehGoalEntry.COLUMN_NAME_TITLE))
            val itemDua =
                getString(getColumnIndexOrThrow(Contracts.TasbeehGoalEntry.COLUMN_NAME_DUA))
            val itemGoal =
                getInt(getColumnIndexOrThrow(Contracts.TasbeehGoalEntry.COLUMN_NAME_GOAL))
            val itemProgress =
                getInt(getColumnIndexOrThrow(Contracts.TasbeehGoalEntry.COLUMN_NAME_PROGRESS))
            val itemActive =
                getInt(getColumnIndexOrThrow(Contracts.TasbeehGoalEntry.COLUMN_NAME_ACTIVE))
            items.add(TasbeehGoal(itemId, itemTitle, itemDua, itemGoal, itemProgress, itemActive))
        }
    }
    cursor.close()
    return items;
}

@Composable
fun CreateGoalDialog(openDialog: MutableState<Boolean>, goalList: SnapshotStateList<TasbeehGoal>) {
    val (newGoalTitle, setNewGoalTitle) = remember { mutableStateOf("") }
    val (newGoalDua, setNewGoalDua) = remember { mutableStateOf("") }
    val (newGoalProgress, setNewGoalProgress) = remember { mutableStateOf("") }
    val context = LocalContext.current

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                openDialog.value = false
            },
            icon = {
                Icon(Icons.Filled.Add, "Localized description")
            },
            title = {
                Text(text = "Create New Goal")
            },
            text = {
                Column() {
                    Spacer(modifier = Modifier.size(10.dp))
                    OutlinedTextField(
                        value = newGoalTitle,
                        onValueChange = { setNewGoalTitle(it) },
                        label = { Text("Title") },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = skin1,
                            cursorColor = skin1,
                            textColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(
                        value = newGoalDua,
                        onValueChange = { setNewGoalDua(it) },
                        label = { Text("Dua") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = skin1,
                            cursorColor = skin1,
                            textColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    OutlinedTextField(
                        value = newGoalProgress,
                        onValueChange = { setNewGoalProgress(it) },
                        label = { Text("Goal") },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = skin1,
                            cursorColor = skin1,
                            textColor = MaterialTheme.colorScheme.onBackground
                        )
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (newGoalDua.isBlank() || newGoalDua.isBlank() || newGoalProgress.isBlank()) {
                            Toast.makeText(
                                context,
                                "Fields cannot be blank",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            val dbhelper = TasbeehGoalDB(context = context)
                            val db = dbhelper.writableDatabase
                            val values = ContentValues().apply {
                                put(
                                    Contracts.TasbeehGoalEntry.COLUMN_NAME_TITLE,
                                    newGoalTitle

                                )
                                put(
                                    Contracts.TasbeehGoalEntry.COLUMN_NAME_DUA,
                                    newGoalDua
                                )
                                put(
                                    Contracts.TasbeehGoalEntry.COLUMN_NAME_GOAL,
                                    newGoalProgress.toInt()
                                )
                                put(Contracts.TasbeehGoalEntry.COLUMN_NAME_PROGRESS, 0)
                                put(Contracts.TasbeehGoalEntry.COLUMN_NAME_ACTIVE, 0)
                            }
                            val newRowId = db?.insert(
                                Contracts.TasbeehGoalEntry.TABLE_NAME,
                                null,
                                values
                            )
                            goalList.add(
                                TasbeehGoal(
                                    id = newRowId!!.toInt(),
                                    title = newGoalTitle,
                                    dua = newGoalDua,
                                    goal = newGoalProgress.toInt(),
                                    progress = 0,
                                    active = 0
                                )
                            )
                            setNewGoalTitle("")
                            setNewGoalDua("")
                            setNewGoalProgress("")
                            openDialog.value = false
                        }
                    }
                ) {
                    Text("Confirm")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        openDialog.value = false
                        setNewGoalTitle("")
                        setNewGoalDua("")
                        setNewGoalProgress("")
                    }
                ) {
                    Text("Dismiss")
                }
            }
        )
    }
}

