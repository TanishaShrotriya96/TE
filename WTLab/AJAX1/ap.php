<?php
   
   //Connect to MySQL Server
   mysqli_connect("localhost", "root", "12345");
   
   //Select Database
   mysqli_select_db("practicals") or die(mysql_error());
   
   // Retrieve data from Query String
   $userid = $_GET['userid'];
  // $pass = $_GET['password'];
   //build query
   $query = "SELECT * FROM login where user="."'".$userid."';";
   
   $qry_result = mysql_query($query) or die(mysql_error());
   
   //Build Result String
   $display_string = "<table>";
   $display_string .= "<tr>";
   $display_string .= "<th>userid</th>";
   $display_string .= "<th>password</th>";
   $display_string .= "</tr>";
   
   // Insert a new row in the table for each person returned
   $row = mysql_fetch_array($qry_result);
   if($row!=null){
      $display_string .= "<tr>";
      $display_string .= "<td>".$row["user"]."</td>";
      $display_string .= "<td>".$row["pass"]."</td>";
      $display_string .= "</tr>";
   
   echo "Query: " . $query . "<br />";
   $display_string .= "</table>";
   echo $display_string;
   }
   else{
        echo "Non-existent User";
   }
?>
