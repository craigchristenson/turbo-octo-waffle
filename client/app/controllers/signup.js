import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service(),

  actions: {
    signup: function() {
      var { email, password } = this.getProperties('email', 'password');
      Ember.$.ajax('http://localhost:3000/users', {
        type: 'POST',
        dataType: 'json',
        data: { user: {email: email, password: password } },
        context: this,
        success: function(data) {
        this.get('session').authenticate('authenticator:devise', email, password).catch((reason) => {
          alert(reason.error);
        });
        },
        error: function(error) {
          alert(error.responseText);
        }
      });
    }
  }
});
