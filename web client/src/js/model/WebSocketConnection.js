import ChattrConnection from './ChattrConnection.js'

var AddressExceptionList = new Set();

AddressExceptionList.add('localhost');
AddressExceptionList.add('127.0.0.1');

export default class WebSocketConnection extends ChattrConnection{

    constructor(uri){
        super(uri);
        this._messageListeners = new Set();
    }

    get socket(){
        if(this.webSocket === undefined) {
            this.webSocket = new WebSocket(this.getURI());
        }
        return this.webSocket;
    }

    isConnectionSupported(proto, address, port){
        return proto === 'wss' || (proto == 'ws' && AddressExceptionList.has(address));
    }

    getAuthenticationParameters(callback){
        function doWork(EventType, Data){
            
        }
    }

    /**
     *
     * @param callback
     */
    registerMessageListener(callback){
        this._messageListeners.add(callback);
    }

    removeMessageListener(callback){
        this._messageListeners.delete(callback);
    }
}


