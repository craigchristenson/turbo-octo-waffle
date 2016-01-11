class UsersController < ApplicationController

  respond_to :json

  def create
    user = User.new(user_params)

    if user.save
      render :json=> user.as_json(:auth_token=>user.authentication_token, :email=>user.email), :status=>201
      return
    else
      warden.custom_failure!
      render :json=> user.errors, :status=>422
    end
  end

  def destroy
    @user = User.find_by authentication_token: params[:user][:token]
    @user.authentication_token = nil
    @user.save!
    render json: { user_id: @user.id }, status: 200
  end

  private

  def user_params
    params.require(:user).permit(:email, :password)
  end
end
