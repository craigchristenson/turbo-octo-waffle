import DeviseAuthenticator from 'ember-simple-auth/authenticators/devise';

export default DeviseAuthenticator.extend({
  serverTokenEndpoint: 'http://localhost:3000/users/sign_in',
  serverTokenEndpointSignOut: 'http://localhost:3000/users/sign_out',
  invalidate: function(authData) {
    return Ember.$.ajax({
      data: { user: {token: authData.token } },
      url:  this.serverTokenEndpointSignOut,
      type: 'DELETE'
    });
  }
});
