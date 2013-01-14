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
#import "UserVO.h"
#import "StringUtil.h"
#import "UIImageView+WebCache.h"
#import "UserInfoCell.h"
#import "FavoriteUtil.h"


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
////////////////////////////////////////////////////////////////////////////////
#pragma mark - Setup & Teardown

- (void)commonInitUserViewController
{
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
    [self showUserInfo];
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

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Actions

////////////////////////////////////////////////////////////////////////////////
#pragma mark - Delegate methods

-(CGFloat)tableView:(UITableView *)tableView heightForRowAtIndexPath:(NSIndexPath *)indexPath
{
    return [UserInfoCell getCellHeight];
}

-(void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    //TODO
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


@end
