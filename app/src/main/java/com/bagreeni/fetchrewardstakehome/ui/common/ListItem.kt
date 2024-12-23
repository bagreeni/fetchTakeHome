package com.bagreeni.fetchrewardstakehome.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ListItem(modifier: Modifier = Modifier, name :String, listId: String?) {
    Row(modifier =
        modifier.border(
            border = BorderStroke(
                width = 2.dp,
                color = MaterialTheme.colorScheme.secondary
            ),
            shape = RoundedCornerShape(8.dp)
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(6.dp)) {
            //show listId
            if (!listId.isNullOrEmpty()) {
                Text(text = "ListID: $listId", color = MaterialTheme.colorScheme.secondary)
            }
            //name
            Text(text = name, color = MaterialTheme.colorScheme.secondary)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ListItemPreview() {
    ListItem(name = "Example 1234", listId = "1")
}
