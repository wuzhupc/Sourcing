//
//  UserViewController.m
//  sourcing
//
//  Created by wuzhu on 13-1-4.
//  Copyright (c) 2013年 wuzhu. All rights reserved.
//

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Imports

#import "UserViewController.h"
#import "UIColor+MGExpanded.h"
#import "ApplicationSet.h"
#import "StringUtil.h"
#import "UIImageView+WebCache.h"
#import "UserInfoCell.h"
#import "FavoriteUtil.h"
#import "UserLoginViewController.h"
#import "DataInterfaceUtil.h"
#import "UserChangePwdViewController.h"
#import "ToastHintUtil.h"


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Types

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Defines & Constants

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Macros

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Interface

@interface UserViewController ()

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private Properties
@property (nonatomic)NSInteger userInfoCount;
@property (nonatomic)BOOL isFirstStart;
@property (nonatomic,strong)UserVO *preUserVO;
@end


////////////////////////////////////////////////////////////////////////////////
#pragma mark - Implementation

@implementation UserViewController

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Synthesize

/* Outlets ********************************************************************/

/* Public *********************************************************************/

/* Private ********************************************************************/
@synthesize userInfoCount = userInfoCount_;
@synthesize isFirstStart = isFirstStart_;
@synthesize preUserVO = preUserVO_;
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitUserViewController
{
    self.isFirstStart = YES;
    preUserVO_ = nil;
    [self.tabBarItem setFinishedSelectedImage:[UIImage imageNamed:@"icon_home_tb_user"] withFinishedUnselectedImage:[UIImage imageNamed:@"icon_home_tb_user"]];
}

- (id)initWithNibName:(NSString*)nibNameOrNil bundle:(NSBundle*)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self)
    {
        [self commonInitUserViewController];
    }
    return self;
}

- (id)initWithCoder:(NSCoder*)aDecoder
{
    self = [super initWithCoder:aDecoder];
    if (self)
    {
        [self commonInitUserViewController];
    }
    return self;
}

- (void)viewDidLoad {
    [super viewDidLoad];
    [self.view setBackgroundColor:CCOLOR_BG_WHITE];
    // your code here
}

-(void)viewWillAppear:(BOOL)animated
{
    [super viewWillAppear:animated];
    [self showUserInfo];
    UserVO *userVO = [ApplicationSet shareData].getUserVO;
    if(self.isFirstStart)
    {
        self.isFirstStart = NO;        
        if(userVO==nil)
            [self showUserLogin];
    }else
    {
        if(self.preUserVO==nil&&userVO != nil)
        {
            //登录成功
            [ToastHintUtil showHint:NSLocalizedString(@"登录成功", @"") parentview:self.view];
        }else if(self.preUserVO!=nil&&userVO!=nil)
        {
            if(![self.preUserVO.password isEqualToString:userVO.password])//修改密码成功
            {
                [ToastHintUtil showHint:NSLocalizedString(@"修改密码成功", @"") parentview:self.view];
            }
        }
    }
    self.preUserVO = [userVO copy];
}

- (void)viewDidUnload {
	// your code here
    [self setImageUserPic:nil];
    [self setLabelUserName:nil];
    [self setLabelUserType:nil];
    [self setButtonLogin_Pwd:nil];
    [self setButtonReg_Account:nil];
    [self setTableviewUserInfo:nil];
    [super viewDidUnload];
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Superclass Overrides

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Public methods

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Private methods
-(void)showUserInfo
{
    UserVO *userVO = [ApplicationSet shareData].getUserVO;
    if(userVO==nil)
    {
        self.userInfoCount = 1;
        [self.imageUserPic setImage:[UIImage imageNamed:@"icon_userpic_bg"]];
        [self.labelUserName setText:NSLocalizedString(@"您还未登录", @"未登录时显示用户名")];
        [self.labelUserType setText:@""];
        [self.buttonLogin_Pwd setTitle:NSLocalizedString(@"登 录", @"") forState:UIControlStateNormal];
        [self.buttonReg_Account setTitle:NSLocalizedString(@"注 册", @"") forState:UIControlStateNormal];
        
        //清除图标提示
        [[UIApplication sharedApplication] setApplicationIconBadgeNumber:0];
        self.tabBarItem.badgeValue = nil;
        
    }else
    {
        self.userInfoCount = [userVO hasInfoCount]+1;
        if([StringUtil isEmpty:userVO.userphoto])
            [self.imageUserPic setImage:[UIImage imageNamed:@"icon_userpic_bg"]];
        else
            [self.imageUserPic setImageWithURL:[NSURL URLWithString:userVO.userphoto] placeholderImage:[UIImage imageNamed:@"icon_userpic_bg"]];
        [self.labelUserName setText:userVO.username];
        [self.labelUserType setText:[userVO getStrUserType]];
        [self.buttonLogin_Pwd setTitle:NSLocalizedString(@"修改密码", @"") forState:UIControlStateNormal];
        [self.buttonReg_Account setTitle:NSLocalizedString(@"切换帐号", @"") forState:UIControlStateNormal];
        
        //图标提示
        [[UIApplication sharedApplication] setApplicationIconBadgeNumber:[userVO getNotReadCount]];
        if([userVO getNotReadCount]>0)
            self.tabBarItem.badgeValue = [NSString stringWithFormat:@"%d", [userVO getNotReadCount]];
        else
            self.tabBarItem.badgeValue = nil;
            
    }
    [self initTableView];
}

-(void)initTableView
{
    //根据userInfoCount调整tableview高度
    CGRect frame = self.tableviewUserInfo.frame;
    frame.size.height = [UserInfoCell getCellHeight] * self.userInfoCount+80.0f;
    self.tableviewUserInfo.frame = frame;
    //重载内容
    [self.tableviewUserInfo reloadData];
}

-(void)showUserLogin
{
    UserLoginViewController *vc = [[UserLoginViewController alloc] init];
    [self presentModalViewController:vc animated:YES];
}

-(void)showChangePwd
{
    UserChangePwdViewController *vc = [[UserChangePwdViewController alloc] init];
    [self presentModalViewController:vc animated:YES];
}

-(void)changeUserInfo:(USER_INFO_TYPE)kinfotype
{
    UserVO *vo = [[ApplicationSet shareData] getUserVO];
    if(kinfotype == USER_INFO_TYPE_FAV||vo==nil)
        return;
    switch (kinfotype) {
        case USER_INFO_TYPE_AUDIT:
            vo.auditcount = 0;
            break;
        case USER_INFO_TYPE_CONSULT:
            vo.consultcount = 0;
            break;
        case USER_INFO_TYPE_NOTIFIER:
            vo.notifiercount = 0;
            break;
        case USER_INFO_TYPE_DECLARE:
            vo.declarecount = 0;
            break;
        default:
            break;
    }
    [[ApplicationSet shareData] setLoginUserInfo:vo saveinfo:YES];
    
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions


- (IBAction)actionLogin_Pwd:(id)sender {
    UserVO *vo = [[ApplicationSet shareData] getUserVO];
    if(vo==nil)
    {
        //登录
        [self showUserLogin];
    }else
    {
        //修改密码
        [self showChangePwd];
    }
}

- (IBAction)actionReg_Account:(id)sender {
    UserVO *vo = [[ApplicationSet shareData] getUserVO];
    if(vo==nil)
    {
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:[DataInterfaceUtil getDataInterface:@"url_reg_progress"]]];
        //注册
    }else
    {
        //切换帐号
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:NSLocalizedString(@"切换帐号", @"") message:NSLocalizedString(@"您确认退出当前登录，更换使用其他帐号登录吗？", @"") delegate:self cancelButtonTitle:NSLocalizedString(@"取消", @"") otherButtonTitles:NSLocalizedString(@"好的", @""),nil];
        [alert show];
    }
    
}

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return [UserInfoCell getCellHeight];
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    UserInfoCell *cell = (UserInfoCell *)[tableView cellForRowAtIndexPath:indexPath];
    if(cell==nil)
        return;
    [self changeUserInfo:cell.userinfotype];
    [cell showDetail:self];
}

-(UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *userInfoCellIdentifier = @"UserInfoCell";
    UserInfoCell *cell = [tableView dequeueReusableCellWithIdentifier:userInfoCellIdentifier];
    if(cell==nil)
    {
        NSArray *nib = [[NSBundle mainBundle]loadNibNamed:@"UserInfoCell" owner:self options:nil];
        cell = [nib objectAtIndex:0];
    }
    UserVO *vo = [[ApplicationSet shareData] getUserVO];
    if(vo==nil)
        [cell setShowInfo:[UserVO getInfoMsgWithFav] userinfotype:USER_INFO_TYPE_FAV];
    else
        [cell setShowInfo:[vo getInfoMsg:indexPath.row] userinfotype:[vo getInfoType:indexPath.row]];
    return cell;
}

-(NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

-(NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return self.userInfoCount;
}
-(void)alertView:(UIAlertView *)alertView clickedButtonAtIndex:(NSInteger)buttonIndex
{
    if(buttonIndex==1)
    {
        [[ApplicationSet shareData] setLoginUserInfo:nil saveinfo:YES];
        [self showUserLogin];
        self.preUserVO = nil;
    }
}
@end
