class ApplicationController < ActionController::Base
  # Prevent CSRF attacks by raising an exception.
  # For APIs, you may want to use :null_session instead.
  protect_from_forgery with: :null_session
  rescue_from StandardError, :with => :handle_error

  protected

  def handle_error(error)
    render :json => {:error => error}
  end

  def http_basic_authenticate!
    authenticate_or_request_with_http_basic do |user, pass|
      authenticated_user = User.find_by_email(user)
      if authenticated_user && Devise.secure_compare(authenticated_user.authentication_token, pass)
        sign_in authenticated_user, store: false
      else
        render json: 'Invalid authorization.'
      end
    end
  end
end
