import Ember from 'ember';

export default Ember.Controller.extend({
  session: Ember.inject.service(),

  actions: {
    authenticate() {
      const { email, password } = this.getProperties('email', 'password');
      this.get('session').authenticate('authenticator:devise', email, password).catch((reason) => {
        alert(reason.error);
      });
    }
  }

});
