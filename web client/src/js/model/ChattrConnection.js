
import NotImplementedError from './exceptions/NotImplementedError.js'
import MethodNotImplementedError from './exceptions/MethodNotImplementedError.js'

/* Taken from http://snipplr.com/view/6889/regular-expressions-for-uri-validationparsing/ on 28 June, 2015*/
var uriRegex = /^([a-z][a-z0-9+.-]*):(?:\/\/((?:(?=((?:[a-z0-9-._~!$&'()*+,;=:]|%[0-9A-F]{2})*))(\3)@)?(?=(\[[0-9A-F:.]{2,}\]|(?:[a-z0-9-._~!$&'()*+,;=]|%[0-9A-F]{2})*))\5(?::(?=(\d*))\6)?)(\/(?=((?:[a-z0-9-._~!$&'()*+,;=:@\/]|%[0-9A-F]{2})*))\8)?|(\/?(?!\/)(?=((?:[a-z0-9-._~!$&'()*+,;=:@\/]|%[0-9A-F]{2})*))\10)?)(?:\?(?=((?:[a-z0-9-._~!$&'()*+,;=:@\/?]|%[0-9A-F]{2})*))\11)?(?:#(?=((?:[a-z0-9-._~!$&'()*+,;=:@\/?]|%[0-9A-F]{2})*))\12)?$/i;

/**
 * This class represents an abstract connection to a chattr instance.
 */
export default class ChattrConnection{
    /**
     * @param {string} uri - this is the uri to open a connection to.
     */
    constructor(uri){
        this._uri = uri;

        var uriParts = uriRegex.exec(uri);
        var proto = uriParts[1]
            , host = uriParts[5]
            , port = uriParts[6];

        if(!this.isConnectionSupported(proto, host, port, uri))
            throw new Error('Connection type not supported');


    }

    getURI() {
        return this._uri;
    }

    /**
     * Checks to see if this ChattrConnection supports the supplied uri.
     * @param {string} proto - protocol of proposed connection
     * @param {string} host - host of proposed connection
     * @param {number} port - port of proposed connection
     */
    isConnectionSupported(proto, host, port, uri) {
        throw new MethodNotImplementedError("isConnectionSupported");
    }


    /**
     * This method
     * @param callback
     */
    getAuthenticationParameters(callback){
        throw new MethodNotImplementedError("getAuthenticationParameters");
    }
}
