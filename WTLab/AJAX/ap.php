<?php
   $dbhost = "localhost";
   $dbuser = "root";
   $dbpass = "12345";
   $dbname = "practicals";  

//Connect to MySQL Server
   $con=mysqli_connect($dbhost, $dbuser, $dbpass,$dbname);

 // Retrieve data from Query String
   $username = $_GET['userid'];
   //build query
   $query = "Select * from login where username='$username'";
   
  //Execute query
  $qry_result = mysqli_query($con,$query);

  $rowss = mysqli_num_rows($qry_result);//to count the number of rows returned by the query
 
   if($rowss<=0)
    echo "Incorrect Username or Password";

   else{
	
   //Build Result String
   $display_string = "<table>";
   $display_string .= "<tr>";
   $display_string .= "<th>Username</th>";
   $display_string .= "</tr>";
   
   // Insert a new row in the table for each person returned
   while($row = mysqli_fetch_array($qry_result))
	{
		$display_string .= "<tr>";
      $display_string .= "<td>$row[username]</td>";
      $display_string .= "</tr>";
	}
   //echo "Query: " . $query . "<br />";
   
   $display_string .= "</table>";
 
   }
   
   echo $display_string
   
 
?>
