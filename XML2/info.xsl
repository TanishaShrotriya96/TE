<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
<HTML>
<BODY bgcolor="RGB(290,256,0)">
<h3><FONT size="12" face="Times New Roman"><b>CLIENTS</b></FONT></h3>
<font size="5">
<OL>
<xsl:for-each select="CLIENTS/CLIENT">
<LI><xsl:value-of select = "NAME"/>
	<UL>
	<LI><xsl:value-of select = "OCCUPATION"/></LI>
	<LI>HOBBIES
		<UL>
		<xsl:for-each select="HOBBIES/HOBBY">
		<LI><xsl:value-of select = "VALUE"/></LI>
		</xsl:for-each>
		</UL>
	</LI>
	</UL>
</LI>
</xsl:for-each>
</OL>
</font>
</BODY>
</HTML>
</xsl:template>
</xsl:stylesheet>
