<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="PrintMe.aspx.cs" Inherits="AccessWebservice.PrintMe" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
    <div>
        <table style="font-family:Arial">
            <tr>
                <td>
                    <asp:TextBox ID="NameTextBox" runat="server"></asp:TextBox><asp:Button ID="Button1" runat="server" Text="Get Message" OnClick="Button1_Click" />
                </td>
                </tr><tr>
                <td>
                    <asp:Label ID="PrintLabel" runat="server"></asp:Label>
                </td>
            </tr>
        </table>
    </div>
    </form>
</body>
</html>
