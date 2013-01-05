//
//  WelcomeViewController.m
//  sourcing
//
//  Created by wuzhu on 12-12-25.
//  Copyright (c) 2012年 wuzhu. All rights reserved.
//

#import "WelcomeViewController.h"
#import "JsonParser.h"
#import "ChannelVO.h"
#import "ApplicationSet.h"
#import "UserVO.h"
#import "MobileUserService.h"

@interface WelcomeViewController ()

@end

@implementation WelcomeViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    CGFloat screenHeight = [UIScreen mainScreen].bounds.size.height;
    
    if ([UIScreen mainScreen].scale == 2.f && screenHeight == 568.0f) {
        self.ivWelcome.image = [UIImage imageNamed:@"Default-568h@2x.png"];
    } else {
        self.ivWelcome.image = [UIImage imageNamed:@"Default.png"];
    }
    [self showNowVerInfo];
    // Do any additional setup after loading the view from its nib.
}

-(void)viewDidAppear:(BOOL)animated
{
    [super viewDidAppear:animated];
    //启动加载栏目信息
    [self loadChannelInfo];
    //TODO 启动判断用户登录
    [self checkUserLogin];
    //显示两秒后切换到主界面
    [self performSelector:@selector(showHome) withObject:nil afterDelay:2];
}

-(void)checkUserLogin
{
    //DEBUG
//    UserVO *u = [[UserVO alloc] init];
//    [u setUseraccount:@"1"];
//    [u setUserid:@"1"];
//    [u setUsername:@"test"];
//    [u setPassword:@"tset"];
//    [UserVO saveLoginUserInfo:u];
    //
    UserVO *userinfo  = [UserVO getLastLoginUserInfo];
    if(userinfo==nil)
        return;
    //验证登录用户信息
    MobileUserService *service = [[MobileUserService alloc] initWithDelegate:self tag:CINT_TAG_CHECKUSER_LOGIN];
    [service userLogin:userinfo.useraccount password:userinfo.password];
}

-(void)loadChannelInfo
{
    if ([ApplicationSet shareData].channels == nil)
    {
        NSArray *array = [ChannelVO initChannelsFromAssets];
        //ChannelVO *cvo = [array objectAtIndex:0];
        //NSLog(@"%@ type:%d",[cvo channelName],[cvo type]);
        [ApplicationSet shareData].channels = array;
    }
}

-(void)showHome
{
    UIStoryboard *stryBoard=[UIStoryboard storyboardWithName:@"Main" bundle:nil];
    self.view.window.rootViewController=[stryBoard instantiateInitialViewController];
}

/**
 * 显示当前版本信息
 */
- (void)showNowVerInfo
{
    NSString *verinfo = [[[NSBundle mainBundle] infoDictionary] objectForKey:@"CFBundleVersion"];
    //显示内部版本用@“CFBundleVersion”  内部版本号
    //显示版本用@“CFBndleShortVersionString” 发布版本号
    NSString *verString = NSLocalizedString(@"Ver:%@", @"版本提示信息");
    self.laVersion.text = [NSString stringWithFormat:verString,verinfo];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)viewDidUnload {
    [self setLaVersion:nil];
    [self setIvWelcome:nil];
    [super viewDidUnload];
}

-(void)serviceResult:(ResponseVO *)result
{
    if (result==nil) {
        return;
    }
    if(result.tag == CINT_TAG_CHECKUSER_LOGIN)
    {
        if([result isSucess])
        {
            ResponseVO *resp = [[ResponseVO alloc] init];
            UserVO *uservo = [JsonParser parseJsonToEntity:result.msg respVO:&resp ref:nil];
            if([resp isSucess])
            {
                [[ApplicationSet shareData] setLoginUserInfo:uservo saveinfo:YES];
            }
            else
            {
                NSLog(@"%@ checkuserlogin JsonParser parseJsonToEntity error:%@",[[self class] description],result.msg);
            }
        }
        else
        {
            NSLog(@"%@ checkuserlogin error:%@",[[self class] description],result.msg);
        }
    }
}
@end
