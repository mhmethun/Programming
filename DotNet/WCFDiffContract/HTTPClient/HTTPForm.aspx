<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="HTTPForm.aspx.cs" Inherits="HTTPClient.HTTPForm" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <asp:Panel ID="Panel1" runat="server">
            <asp:TextBox ID="nameTextBox" runat="server"></asp:TextBox>
            <asp:Button ID="nameButton" runat="server" Text="Button" OnClick="nameButton_Click" />
        </asp:Panel>
    </div>
        <p>
            <asp:Label ID="nameLabel" runat="server" Text=""></asp:Label>
        </p>
    </form>
</body>
</html>
