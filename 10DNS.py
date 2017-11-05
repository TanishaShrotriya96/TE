import socket

print "Welcome to DNS to IP Address"
URL=raw_input("Enter URL")

addr1=socket.gethostbyname(URL)

print("The IP address is "+addr1)
print"Welcome IP address to DNS"
IP=raw_input("Enter IP Address")
addr6=socket.gethostbyaddr(IP)
print ("Host is " + addr6)
