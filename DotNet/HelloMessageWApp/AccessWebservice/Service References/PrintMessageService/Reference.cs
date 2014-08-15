﻿//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated by a tool.
//     Runtime Version:4.0.30319.18408
//
//     Changes to this file may cause incorrect behavior and will be lost if
//     the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace AccessWebservice.PrintMessageService {
    
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ServiceModel.ServiceContractAttribute(ConfigurationName="PrintMessageService.MessageServiceSoap")]
    public interface MessageServiceSoap {
        
        // CODEGEN: Generating message contract since element name name from namespace http://tempuri.org/ is not marked nillable
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GetMessage", ReplyAction="*")]
        AccessWebservice.PrintMessageService.GetMessageResponse GetMessage(AccessWebservice.PrintMessageService.GetMessageRequest request);
        
        [System.ServiceModel.OperationContractAttribute(Action="http://tempuri.org/GetMessage", ReplyAction="*")]
        System.Threading.Tasks.Task<AccessWebservice.PrintMessageService.GetMessageResponse> GetMessageAsync(AccessWebservice.PrintMessageService.GetMessageRequest request);
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class GetMessageRequest {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="GetMessage", Namespace="http://tempuri.org/", Order=0)]
        public AccessWebservice.PrintMessageService.GetMessageRequestBody Body;
        
        public GetMessageRequest() {
        }
        
        public GetMessageRequest(AccessWebservice.PrintMessageService.GetMessageRequestBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class GetMessageRequestBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string name;
        
        public GetMessageRequestBody() {
        }
        
        public GetMessageRequestBody(string name) {
            this.name = name;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.ServiceModel.MessageContractAttribute(IsWrapped=false)]
    public partial class GetMessageResponse {
        
        [System.ServiceModel.MessageBodyMemberAttribute(Name="GetMessageResponse", Namespace="http://tempuri.org/", Order=0)]
        public AccessWebservice.PrintMessageService.GetMessageResponseBody Body;
        
        public GetMessageResponse() {
        }
        
        public GetMessageResponse(AccessWebservice.PrintMessageService.GetMessageResponseBody Body) {
            this.Body = Body;
        }
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
    [System.Runtime.Serialization.DataContractAttribute(Namespace="http://tempuri.org/")]
    public partial class GetMessageResponseBody {
        
        [System.Runtime.Serialization.DataMemberAttribute(EmitDefaultValue=false, Order=0)]
        public string GetMessageResult;
        
        public GetMessageResponseBody() {
        }
        
        public GetMessageResponseBody(string GetMessageResult) {
            this.GetMessageResult = GetMessageResult;
        }
    }
    
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public interface MessageServiceSoapChannel : AccessWebservice.PrintMessageService.MessageServiceSoap, System.ServiceModel.IClientChannel {
    }
    
    [System.Diagnostics.DebuggerStepThroughAttribute()]
    [System.CodeDom.Compiler.GeneratedCodeAttribute("System.ServiceModel", "4.0.0.0")]
    public partial class MessageServiceSoapClient : System.ServiceModel.ClientBase<AccessWebservice.PrintMessageService.MessageServiceSoap>, AccessWebservice.PrintMessageService.MessageServiceSoap {
        
        public MessageServiceSoapClient() {
        }
        
        public MessageServiceSoapClient(string endpointConfigurationName) : 
                base(endpointConfigurationName) {
        }
        
        public MessageServiceSoapClient(string endpointConfigurationName, string remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public MessageServiceSoapClient(string endpointConfigurationName, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(endpointConfigurationName, remoteAddress) {
        }
        
        public MessageServiceSoapClient(System.ServiceModel.Channels.Binding binding, System.ServiceModel.EndpointAddress remoteAddress) : 
                base(binding, remoteAddress) {
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        AccessWebservice.PrintMessageService.GetMessageResponse AccessWebservice.PrintMessageService.MessageServiceSoap.GetMessage(AccessWebservice.PrintMessageService.GetMessageRequest request) {
            return base.Channel.GetMessage(request);
        }
        
        public string GetMessage(string name) {
            AccessWebservice.PrintMessageService.GetMessageRequest inValue = new AccessWebservice.PrintMessageService.GetMessageRequest();
            inValue.Body = new AccessWebservice.PrintMessageService.GetMessageRequestBody();
            inValue.Body.name = name;
            AccessWebservice.PrintMessageService.GetMessageResponse retVal = ((AccessWebservice.PrintMessageService.MessageServiceSoap)(this)).GetMessage(inValue);
            return retVal.Body.GetMessageResult;
        }
        
        [System.ComponentModel.EditorBrowsableAttribute(System.ComponentModel.EditorBrowsableState.Advanced)]
        System.Threading.Tasks.Task<AccessWebservice.PrintMessageService.GetMessageResponse> AccessWebservice.PrintMessageService.MessageServiceSoap.GetMessageAsync(AccessWebservice.PrintMessageService.GetMessageRequest request) {
            return base.Channel.GetMessageAsync(request);
        }
        
        public System.Threading.Tasks.Task<AccessWebservice.PrintMessageService.GetMessageResponse> GetMessageAsync(string name) {
            AccessWebservice.PrintMessageService.GetMessageRequest inValue = new AccessWebservice.PrintMessageService.GetMessageRequest();
            inValue.Body = new AccessWebservice.PrintMessageService.GetMessageRequestBody();
            inValue.Body.name = name;
            return ((AccessWebservice.PrintMessageService.MessageServiceSoap)(this)).GetMessageAsync(inValue);
        }
    }
}